from flask import Flask, request
#from flack import request
import math

app = Flask(__name__)
@app.route('/')
def start():
    start = """
        <!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>lab11</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
    <p>Через / указать нужное выражение и числа (пример: /sun/10.0/20.0)</p>
    <p>1.Сложение sum</p>
    <p>2.Вычитание min</p>
    <p>3.Умножение mul</p>
    <p>4.Деление deli</p>
    <p>5.Корень sqrt</p>
    <p>6.Степень ste</p>
    <p>7.Целочисленное деление cel </p>
    <p>8.Остаток от деления ost</p>
    <p>Можно выбрать единицы счисления ?mode= (пример: /sin/30.0?mode=degrees) </p>
    <p>1.Синус sin</p>
    <p>2.Косинус cos</p>
    <p>3.Тангенс tg</p>
</body>
</html>
    """
    return start
@app.route('/sum/<a>/<b>')
def sum (a,b):
    a = float(a)
    b = float(b)
    return str(a+b)
@app.route('/min/<a>/<b>')
def min (a,b):
    a = float(a)
    b = float(b)
    return str(a-b)
@app.route('/mul/<a>/<b>')
def mul (a,b):
    a = float(a)
    b = float(b)
    return str(a*b)
@app.route('/deli/<a>/<b>')
def deli (a,b):
    a = float(a)
    b = float(b)
    if (b != 0):
        return str(a/b)
    else:
        return (str('делитель ноль'))
@app.route('/sin/<a>')
def sin (a):
    a = float(a)
    mode = request.args.get('mode', default='radians')
    if mode == 'degrees':
        return str(math.sin(math.radians(a)))
    else:
        return str(math.sin(a))
@app.route('/cos/<a>')
def cos(a):
    a = float(a)
    mode = request.args.get('mode', default='radians')
    if mode == 'degrees':
        return str(math.cos(math.radians(a)))
    else:
        return str(math.cos(a))
@app.route('/tg/<a>')
def tg(a):
    a = float(a)
    mode = request.args.get('mode', default='radians')
    if mode == 'degrees':
        if (a == 90 or a == 270):
            return str ('не существует')
        else:
            return str(math.tan(math.radians(a)))
    else:
        if (a == (math.pi/2) or a == (3*math.pi/2)):
            return str ('не существует')
        else:
            return str(math.tan(a))
@app.route('/sqrt/<a>')
def sqrt (a):
    a = float(a)
    if (a < 0):
        return str('меньше 0')
    else:
        return str(math.sqrt(a))
@app.route('/ste/<a>/<b>')
def ste (a,b):
    a = float(a)
    b = float(b)
    return str(a**b)
@app.route('/ost/<a>/<b>')
def ost (a,b):
    a = float(a)
    b = float(b)
    if (b != 0):
        return str(a % b)
    else:
        return (str('делитель ноль'))
@app.route('/cel/<a>/<b>')
def cel (a,b):
    a = float(a)
    b = float (b)
    if (b != 0):
        return str(a // b)
    else:
        return (str('делитель ноль'))
#app.run(host='0.0.0.0', debug = True)