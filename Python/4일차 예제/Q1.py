"""
a:b:c:d
문자열의 split와 join 함수를 사용하여 위 문자열을 다음과 같이 고치시오.
a#b#c#d
"""
str1 = "a:b:c:d"
str2 = str1.replace(":","#")
print(str1)
print(str2)

print("#".join(str1.split(":")))
