from django.urls import path
from .views import homePageView, homePageView2

urlpatterns = [
    path("", homePageView, name="home"),
    path("hi", homePageView2, name="home2"),
    path("hi/babo.it", homePageView2, name="home2"),
]