import random
from locust import HttpUser, task, between

#locust -f AddPosts.py
class AddPosts(HttpUser):
    # 스레드 각각 사이에 1~2초 여유를 주겠다.
    waite_item = between(5, 5)

    def on_start(self):
        self.client.post("/users/sign-in", json={
                                                    "email": "user1",
                                                    "password": "password1"
                                                })

    @task
    def add_post(self):
        self.client.post("/posts", json={
            "name" : "테스트 게시글" + str(random.randint(1, 100000)),
            "contents": "테스트 컨텐츠" + str(random.randint(1, 100000)),
            "categoryId" : str(random.randint(1, 6))
        })
