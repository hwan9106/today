import random

game = int(input("몇게임?"))
for i in range(0, game):
    lotto = random.sample(range(1, 46), 6)
    lotto.sort()
    print(i + 1, "게임 : ", lotto)
