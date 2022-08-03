from flask import Flask, render_template

app = Flask(__name__)


@app.route("/")
def home():
    return render_template("index3.html")


@app.route("/song")
def read_txt():
    f = open('song.txt','r', encoding='UTF-8')
    data = f.read()
    f.close()
    return data


if __name__ == '__main__':
    app.run()
