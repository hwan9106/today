import game.sound.echo
game.sound.echo.echo_test()

from game.sound import echo
echo.echo_test()

from game.sound.echo import echo_test
echo_test()

from function import module2
print(module2.mul(3, 4))

from game.sound import *
echo.echo_test()
wave.wave_test()

from game.graphic.render import render_test
render_test()

import Ex02_Class
a = Ex02_Class.FourCal()

a.set_data(44, 6)
print(a.add())