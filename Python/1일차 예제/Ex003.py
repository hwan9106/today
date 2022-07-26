# 출력에 대하여 알아보자
# 형식에 맞추어 출력하기

# , ! 기호를 사용해서 문자열의 방식을 지정해 줄 수 있습니다. ! 뒤에 s, r, a 가 붙을 수 있는데,
# s 는 srt(), r은 repr(), a 는 ascii() 를 의미 합니다.
# 여기서 ascii() 는 repr 과 마찬가지로 출력할 수 있는 문자열을 리턴 해주는데,
# 조금 다른 점은 이스케이프 문자(\x, \u)도 같이 표현해준다는 것입니다.

name = '한사람'
print(name)
print(str(name))
print(repr(name))
print(ascii(name))
print()

print('Python is {0} good {1} language'.format('좋은', '프로그램'))
print('Python is {0!s} good {1!s} language'.format('좋은', '프로그램'))
print('Python is {0!r} good {1!r} language'.format('좋은', '프로그램'))
print('Python is {0!a} good {1!a} language'.format('좋은', '프로그램'))
print()

print('Python is {0} good {1} language'.format('very', 'programming'))
print('Python is {0!s} good {1!s} language'.format('very', 'programming'))
print('Python is {0!r} good {1!r} language'.format('very', 'programming'))
print('Python is {0!a} good {1!a} language'.format('very', 'programming'))
print('-' * 40)