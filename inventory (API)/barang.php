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

if ($_SERVER["REQUEST_METHOD"] !== "GET") {
    response("error", "Hanya menerima metode GET");
}

if (isset($_GET['id'])) {
    $id = intval($_GET['id']);
    
    $stmt = $conn->prepare("SELECT * FROM barang WHERE id = ?");
    $stmt->bind_param("i", $id);
    $stmt->execute();
    $result = $stmt->get_result();
    $barang = $result->fetch_assoc();

    if (!$barang) {
        response("error", "Barang tidak ditemukan");
    }

    $data = [
        "id" => $barang['id'],
        "nama_barang" => $barang['nama_barang'],
        "stok" => $barang['stok'],
        "harga" => $barang['harga'],
        "deskripsi" => $barang['deskripsi']
    ];

    response("success", "Detail barang ditemukan", $data);
}

$query = "SELECT id, nama_barang, stok, harga, deskripsi FROM barang ORDER BY id DESC";
$result = $conn->query($query);

if (!$result) {
    response("error", "Gagal mengambil data: " . $conn->error);
}

$barang_list = [];
while ($row = $result->fetch_assoc()) {
    $barang_list[] = [
        "id" => $row['id'],
        "nama_barang" => $row['nama_barang'],
        "stok" => $row['stok'],
        "harga" => $row['harga'],
        "deskripsi" => $row['deskripsi']
    ];
}

response("success", "Daftar barang ditemukan", $barang_list);
?>