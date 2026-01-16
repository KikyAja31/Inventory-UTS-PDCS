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

$username     = isset($_POST['username']) ? $_POST['username'] : null;
$password     = isset($_POST['password']) ? $_POST['password'] : null;
$nama_lengkap = isset($_POST['nama_lengkap']) ? $_POST['nama_lengkap'] : null;

if (empty($username) || empty($password) || empty($nama_lengkap)) {
    response("error", "Username, password, dan nama lengkap tidak boleh kosong");
}

$checkStmt = $conn->prepare("SELECT username FROM users WHERE username = ?");
$checkStmt->bind_param("s", $username);
$checkStmt->execute();
$checkResult = $checkStmt->get_result();

if ($checkResult->num_rows > 0) {
    response("error", "Username sudah terdaftar, silakan gunakan yang lain");
}

$stmt = $conn->prepare("INSERT INTO users (username, password, nama_lengkap) VALUES (?, ?, ?)");
$stmt->bind_param("sss", $username, $password, $nama_lengkap);

if ($stmt->execute()) {
    $data_user = [
        "id_user"  => $conn->insert_id,
        "username" => $username,
        "nama"     => $nama_lengkap
    ];
    response("success", "Registrasi Berhasil", $data_user);
} else {
    response("error", "Gagal melakukan registrasi: " . $conn->error);
}

$stmt->close();
$conn->close();
?>