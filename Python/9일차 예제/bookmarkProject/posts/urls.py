from django.urls import path
from .views import BookmarkLV, BookmarkDV, HomePageView

urlpatterns = [
    path("", HomePageView.as_view(), name="home"),
    path("list/", BookmarkLV.as_view(), name="list"),
    path("detail/<int:pk>/", BookmarkDV.as_view(), name="detail"),
]