from django.shortcuts import render

# Create your views here.
from django.views.generic import ListView, DetailView
from .models import Bookmark


class HomePageView(ListView):
    model = Bookmark
    template_name = "home.html"


class BookmarkLV(ListView):
    model = Bookmark
    template_name = "bookmark_list.html"


class BookmarkDV(DetailView):
    model = Bookmark
    template_name = "bookmark_detail.html"

