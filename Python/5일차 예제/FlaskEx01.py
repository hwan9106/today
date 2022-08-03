from flask import Flask

app = Flask(__name__)


@app.route("/")
def hello_world():
    return "<p>Hello, World! 반갑다 플라스크!!!</p>"


@app.route('/main')
def main():
    return 'main page'


if __name__ == '__main__':
    app.run()