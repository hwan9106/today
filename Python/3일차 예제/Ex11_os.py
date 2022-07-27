import os

f = os.popen("dir")
print(f.read())
f.close()

f = os.popen("tree")
print(f.read())
f.close()

import calendar

print(calendar.calendar(2022))
print(calendar.calendar(2022,8))