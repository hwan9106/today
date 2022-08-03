import matplotlib.font_manager as fm
import matplotlib as mpl
# 설치된 폰트 출력
font_list = [font.name for font in fm.fontManager.ttflist]
print(font_list)
#for font in font_list:
#    print(font)

print("*" * 80)

print('버전 : ', mpl.__version__ )
print('설치 위치 : ', mpl.__file__ )
print('설정 위치 : ', mpl.get_configdir() )
print('캐시 위치 : ', mpl.get_cachedir )
print('설정 파일 위치 : ', mpl.matplotlib_fname())
font_list = fm.findSystemFonts(fontpaths=None, fontext='ttf')
print(len(font_list), "개")
for font in font_list:
    print(font)