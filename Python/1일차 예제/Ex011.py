# 딕셔너리 자료형 : 자바의 맵과 비슷하다.
dic = {'name': 'pey', 'phone': '0119993323', 'birth': '1118'}
print(type(dic), " : ", dic)

print(dic['name'])
print(dic['phone'])
print(dic['birth'])

dic['gender'] = '남자'  # 추가
print(type(dic), " : ", dic)

del dic['birth']  # 삭제
print(type(dic), " : ", dic)
