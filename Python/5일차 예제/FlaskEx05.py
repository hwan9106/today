from flask import Flask, render_template

app = Flask(__name__)


@app.route("/")
def home():
    return render_template("index.html")


@app.route('/template1')
@app.route('/template1/<tempid>')
def template_test(tempid=None):
    sports = ['야구', '축구', '농구']
    person = {'name': '한사람', 'age': 34, 'gender': False}
    persons = [
        {'name': '한사람', 'age': 34, 'gender': False},
        {'gender': True, 'name': '두사람', 'age': 54},
        {'age': 22, 'name': '세사람', 'gender': False}
        ]
    return render_template('template1.html', tempid=tempid, sports=sports, person=person, persons=persons)


if __name__ == '__main__':
    app.run()
