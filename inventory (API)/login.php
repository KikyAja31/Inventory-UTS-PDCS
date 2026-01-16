<?php
require "config.php";

function response($status, $msg, $data = null) {
    header('Content-Type: application/json');
    echo json_encode([
        "status" => $status,
        "message" => $msg,
        "data" => $data
    ]);
    exit;
}

if ($_SERVER["REQUEST_METHOD"] !== "POST") {
    response("error", "Gunakan metode POST");
}

$username = $_POST['username'] ?? null;
$password = $_POST['password'] ?? null;

if (empty($username) || empty($password)) {
    response("error", "Username dan password tidak boleh kosong");
}

// Sesuaikan kolom: id, username, password (sesuai tabel users Anda)
$stmt = $conn->prepare("SELECT id, username, password FROM users WHERE username = ?");
$stmt->bind_param("s", $username);
$stmt->execute();
$result = $stmt->get_result();
$user = $result->fetch_assoc();

if ($user) {
    // Gunakan password_verify jika di register menggunakan password_hash
    if ($password === $user['password']) {
        $data_user = [
            "id"       => $user['id'],
            "username" => $user['username']
        ];
        response("success", "Login Berhasil", $data_user);
    } else {
        response("error", "Password salah");
    }
} else {
    response("error", "Username tidak ditemukan");
}