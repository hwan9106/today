import numpy as np

# NumPy 객체를 저장하고 로드하는 방법
ar = np.array([1, 2, 3, 4, 5, 6])
np.save('np_array.npy', ar)

ar2 = np.load('np_array.npy')

print(ar)
print(ar2)

# NumPy 배열을 .csv 또는 .txt 파일과 같은 일반 텍스트 파일로 저장할 수 있습니다
np.savetxt('np_array.txt', ar)
ar3 = np.loadtxt('np_array.txt')
print(ar3)
