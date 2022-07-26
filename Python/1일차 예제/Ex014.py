from setuptools import sic

money = True
if not money:
    print('택시타고가~~~')
else:
    print('걸어가~~~')


if money:
    print("택시를", sep=' ', end=' ')
print("타고", sep=' ', end=' ')
print("가라")

print( 1, 2, 3, 4, end=' : ')
print( 1, 2, 3, 4, sep='', end='\n\n\n')
print( 1, 2, 3, 4, sep='*')