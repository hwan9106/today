from flask import Flask, render_template, request, session, redirect, url_for

app = Flask(__name__)


@app.route("/")
def home():
    return "<a href='login_form'>로그인하러 가기</a>"


@app.route('/login_form')
def login_form():
    return render_template('login_form.html')


@app.route('/logout')
def logout():
    session['logged_in'] = False   # 세션에 저장된 값을 바꾼다.
    session.pop('username', None)   # 세션에 저장된 값을 제거
    #return redirect(url_for('home'))  # 지정 함수로 이동하기
    return redirect("/")  # 지정 주소로 이동하기


# @app.route('/login', methods=['POST'])  # POST만 처리
# @app.route('/login', methods=['GET'])  # GET만 처리
@app.route('/login', methods=['GET', 'POST'])  # GET/POST 처리
def login():
    if request.method == 'POST':  # 전송이 POST인 경우에만
        # request.form['name속성값']   # 넘어온 값 받기
        if request.form['username'] == 'user' and request.form['password'] == '1234':
            session['logged_in'] = True  # 세션에 저장
            session['username'] = request.form['username']
            return request.form['username'] + " 님 환영합니다.<br> <a href='logout'>로그아웃</a>"
        else:
            return '로그인 정보가 맞지 않습니다.'
    else:
        return '잘못된 접근'


# 세션을 사용하기 위해서는 반드시 키 값을 지정해야 한다. 값은 아무거나 상관없다.
app.secret_key = 'qwerty'

if __name__ == '__main__':
    app.run()
