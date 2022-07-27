import module  # 같은 폴더에 있는 모듈 참조

print(module.add(3, 5))
print(module.sub(13, 4))

# 같은 폴더의 module.py의 add, sub 함수 임포트
from module import add, sub
print(add(3, 5))
print(sub(13, 4))

# 같은 폴더의 module.py의 모든 함수 임포트
from module import *
print(add(3, 5))
print(sub(13, 4))

# from 하위폴더명 import 파일명
from function import module2
print(module2.mul(3, 5))
print(module2.div(13, 4))
