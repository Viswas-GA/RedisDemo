

# RedisDemo
Spring Boot Product Search API with Redis Caching .🔹 Problem Without Cache  Every time a user searches for a product (e.g., "Best Pizza"), the request goes to the database (MySQL). This is slow if many users repeat the same query.With Cache We use Redis (an in-memory database) to temporarily store frequently requested data.

🏗️ Spring Boot + MySQL + Redis
✅ Step 1: Add Redis dependency (in pom.xml)

✅ Step 2: Enable Caching (in Application class)

✅ Step 3: Configure Redis (in application.properties)

spring.cache.type=redis
spring.data.redis.host=localhost
spring.data.redis.port=6379

✅ Step 4: Add Cache in Service Layer
🕵️ What Happened?
@Cacheable → first time goes to DB, next time Redis gives the result.
@CachePut → updates both DB + Redis.
@CacheEvict → removes from Redis when deleted.

# 1. Check running containers
docker ps  
# 2. Check all containers (including stopped ones)
docker ps -a  
# 3. Start the existing one
docker start my-redis  
# 4. Remove the old one and create new
docker rm -f my-redis  
# 5. Run a new Redis container
docker run --name my-redis -d -p 6379:6379 redis  
# 6. Run redis-cli inside container
docker exec -it my-redis redis-cli  
