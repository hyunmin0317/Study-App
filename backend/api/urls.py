# api/urls.py
from django.urls import path, include
from .views import RegistrationAPI, LoginAPI, UserAPI

urlpatterns = [
    path("signup", RegistrationAPI.as_view()),
    path("signin", LoginAPI.as_view()),
    path("user", UserAPI.as_view()),
]