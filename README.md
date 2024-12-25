# 🚀 KHOTIXS API USER PROFILE SERVICE

Description: Allows administrators to update user details, enable accounts for platform access, and disable accounts to restrict access while preserving user data.

---
## API Endpoints <a name="api-endpoints"></a>

<details><summary>Show/Hide</summary>

- **dev** ➕
    - **Description:** Endpoint to dev.
    - **HTTP Method:** GET
    - **Endpoint:** `http://34.121.176.49:8888/user-profile-service/dev`
    - **Request Body:**

```json
{"body": "nobody"}
```
- **Response:**
```json
{"body": "nobody"}
```
- **get-all-user** ➕
    - **Description:**
    - **HTTP Method:** GET
    - **Endpoint:** `http://localhost:8086/api/v1/user-profiles`
    - **Request Body:**

```json
{"body": "nobody"}
```
- **Response:**
```json
    {
  "content": [
    {
      "id": "6769295b75ff1575a36a5625",
      "fullName": "seakngim",
      "gender": "Female",
      "dob": "2002-01-23",
      "phoneNumber": "124567890",
      "address": "svayreang",
      "avatar": "file.png",
      "status": 1,
      "position": "Spring",
      "email": "sothea.phan@example.com",
      "businessName": "test"
    }
  ],
  "pageable": {
    "pageNumber": 0,
    "pageSize": 25,
    "sort": {
      "empty": false,
      "sorted": true,
      "unsorted": false
    },
    "offset": 0,
    "paged": true,
    "unpaged": false
  },
  "last": true,
  "totalPages": 1,
  "totalElements": 13,
  "size": 25,
  "number": 0,
  "sort": {
    "empty": false,
    "sorted": true,
    "unsorted": false
  },
  "first": true,
  "numberOfElements": 13,
  "empty": false
}
```

- **update-by-email** ➕
    - **Description:** Endpoint to update-by-email.
    - **HTTP Method:** PATCH
    - **Endpoint:** `http://localhost:8086/api/v1/user-profiles/sothea.phan@example.com`
    - **Request Body:**

```json
{
  "fullName": "Seakngim Phal",
  "gender": "Female",
  "position": "Spring"
}
```
- **Response:**
```json
{
  "id": "6769295b75ff1575a36a5625",
  "fullName": "Seakngim Phal",
  "gender": "Female",
  "dob": "2002-01-23",
  "phoneNumber": "124567890",
  "address": "svayreang",
  "avatar": "file.png",
  "status": 1,
  "position": "Spring",
  "email": "sothea.phan@example.com",
  "businessName": "test"
}
```

- **get-user** ➕
    - **Description:** Endpoint to get-user.
    - **HTTP Method:** GET
    - **Endpoint:** `http://localhost:8086/api/v1/user-profiles/sothea.phan@example.com`
    - **Request Body:**

```json
{"body": "nobody"}
```
- **Response:**
```json
{
  "id": "6769295b75ff1575a36a5625",
  "fullName": "seakngim",
  "gender": "Female",
  "dob": "2002-01-23",
  "phoneNumber": "124567890",
  "address": "svayreang",
  "avatar": "file.png",
  "status": 1,
  "position": "Spring",
  "email": "sothea.phan@example.com",
  "businessName": "test"
}
```
- **disable-user** ➕
    - **Description:** Endpoint to disable-user.
    - **HTTP Method:** PUT
    - **Endpoint:** `http://localhost:8086/api/v1/user-profiles/sothea.phan@example.com/disable`
    - **Request Body:**

```json
{"body": "nobody"}
```
- **Response:** 1

- **enable-user** ➕
    - **Description:** Endpoint to enable-user.
    - **HTTP Method:** PUT
    - **Endpoint:** `http://localhost:8086/api/v1/user-profiles/sothea.phan@example.com/disable`
    - **Request Body:**

```json
{"body": "nobody"}
```
- **Response:** 1

</details>