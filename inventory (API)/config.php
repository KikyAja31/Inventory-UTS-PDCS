<?php
$host = "localhost";
$user = "root";
$pass = "root";
$db   = "inventory";

$conn = mysqli_connect($host, $user, $pass, $db);

if (!$conn) {
    header('Content-Type: application/json');
    echo json_encode([
        "status" => "error",
        "message" => "Koneksi Database Gagal: " . mysqli_connect_error()
    ]);
    exit;
}