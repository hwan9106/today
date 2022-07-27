try:
    age=int(input('나이를 입력하세요: '))
except:
    print('입력이 정확하지 않습니다.')
else:  # 예외가 없을 경우에 실행되는 명령들
    if age <= 18:
        print('미성년자는 출입금지입니다.')
    else:
        print('환영합니다.')