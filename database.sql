CREATE DATABASE inventory_db;

USE inventory_db;

CREATE TABLE stock (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nama_barang VARCHAR(255) NOT NULL,
    jumlah_stok INT NOT NULL,
    nomor_seri VARCHAR(100) NOT NULL,
    additional_info JSON,
    gambar_barang LONGBLOB, -- or you can save the path as VARCHAR depending on your preference
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by VARCHAR(100),
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    updated_by VARCHAR(100)
) ENGINE InnoDB;

select *
from stock;

truncate stock;