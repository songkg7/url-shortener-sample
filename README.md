# URL Shortener Sample

대규모 시스템 설계 기초 8장. URL 단축기 설계의 간단한 구현 예제입니다.

책에서는 유일 ID 생성기를 별도로 요구하지만, 문제를 단순하게 하고 URL 단축 설계에만 집중하기 위해 ID 로는 ULID 를 사용합니다.

## Quick Start

```bash
$ docker compose up -d
```

```bash
curl -X POST --location "http://localhost:8080/api/v1/shorten" \
    -H "Content-Type: application/json" \
    -d "{
            \"longUrl\": \"https://www.google.com\"
        }"
```

```bash
curl -X GET "http://localhost:8080/api/v1/{{shortUrl}}"
```
