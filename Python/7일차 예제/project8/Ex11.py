import matplotlib.pyplot as plt
import numpy as np

x = np.linspace(0, 2 * np.pi, 200)
print(x)
y = np.sin(x)
print(y)

fig, ax = plt.subplots()
ax.plot(x, y)
plt.show()
