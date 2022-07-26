import sys  # 모듈 사용

if len(sys.argv) !=3:
    print("사용 형식이 잘못되었습니다.");
    print("사용 형식] > python {} 원본파일명 사본파일명".format(__file__));
else:
    sf = open(sys.argv[1], 'r')
    df = open(sys.argv[2], 'w')  # 추가모드 : 없으면 만들고 있으면 추가한다.
    df.write(sf.read())
    sf.close()
    df.close()
    print("{{{0}}}을 {{{1}}}로 복사했습니다.".format(sys.argv[1],sys.argv[2]))



