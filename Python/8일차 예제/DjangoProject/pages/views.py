from django.http import HttpResponse


def homePageView(request):
    return HttpResponse("Hello, World!")


def homePageView2(request):
    return HttpResponse("반갑다 세상아!!!!")


