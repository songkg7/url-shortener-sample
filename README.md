# URL Shortener Sample

대규모 시스템 설계 기초 8장. URL 단축기 설계의 간단한 구현 예제입니다.

책에서는 전역적 유일성을 보장하는 ID 생성기를 요구하지만, 본 예제에서는 ULID 를 활용하며 다소 단순화되어 있습니다.

## Quick Start

```bash
$ docker compose up -d
```

```bash
curl -X POST --location "http://localhost:8080/api/v1/shorten" \
    -H "Content-Type: application/json" \
    -d "{
            \"longUrl\": \"https://www.google.com/search?q=url+shortener&sourceid=chrome&ie=UTF-8\"
        }"
```

```bash
curl -X GET "http://localhost:8080/api/v1/{{shortUrl}}"
```
