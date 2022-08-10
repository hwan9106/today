from django.shortcuts import render

# Create your views here.
from django.views.generic import ListView
from .models import Main


class HomePageView(ListView):
    model = Main
    template_name = "index.html"

