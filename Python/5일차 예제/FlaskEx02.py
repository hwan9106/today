from flask import Flask

app = Flask(__name__)


@app.route("/")
def hello_world():
    return "<p>Hello, World! 반갑다 플라스크!!!</p>"


@app.route("/user/<username>")
def user(username):
    return "{0}님 반갑습니다.".format(username)


@app.route("/user/<username>/<int:age>")
def user2(username, age):
    return "{0}님 {1}살이네 행님이라 불러라!!!".format(username, age)


# 로그 출력하기
@app.route('/logging')
def logging_test():
    test = 1
    app.logger.debug('디버깅 필요')
    app.logger.warning(str(test) + " 라인")
    app.logger.error('에러발생')
    return "로깅 끝"


if __name__ == '__main__':
    app.run()