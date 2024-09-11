# Stock API Spec

## Create Stock

Endpoint : POST /api/stock/create

Request Body :

```json
{
  "namaBarang" : "Barang A",
  "jumlahStok" : "100",
  "nomorSeri" : "A123",
  "createdBy" : "user1",
  "id": 1
}
```

Response Body (Success) :

```json
{
  "data" : {
    "id" : "randomstring",
    "namaBarang" : "Barang A",
    "jumlahStok" : "100",
    "nomorSeri" : "A123",
    "createdBy" : "user1",
  }
}
```

Response Body (Failed) :

```json
{
  "errors" : "Stock is not found"
}
```
## Get Stock

Endpoint : GET /api/stock


Response Body (Success) :

```json
{
  "data" : {
    "id": 1,
    "namaBarang": "Product A",
    "jumlahStok": 10,
    "nomorSeri": "SER12345"
  }
}
```
## Get Stock Detail

Endpoint : GET /api/stock/{id}

Response Body (Success) :

```json
{
  "data" : {
    "id": 1,
    "namaBarang": "Product A",
    "jumlahStok": 10,
    "nomorSeri": "SER12345"
  }
}
```

Response Body (Failed) :

```json
{
  "errors" : "Stock is not found"
}
```

## Update Stock

Endpoint : PUT /api/stock/{id}


Request Body :

```json
{
  "id": 1,
  "namaBarang": "Updated Product",
  "jumlahStok": 5,
  "nomorSeri": "SER12345"
}
```

Response Body (Success) :

```json
{
  "data" : {
    "id": 1,
    "namaBarang": "Updated Product",
    "jumlahStok": 5,
    "nomorSeri": "SER12345"
  }
}
```

## Remove Stock

Endpoint : DELETE /api/stock/{id}


Response Body (Success) :

```json
{
  "data" : "OK"
}
```

Response Body (Failed) :

```json
{
  "errors" : "Stock is not found"
}
```