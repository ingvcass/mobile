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
@app.route('/sum/<float:a>/<float:b>')
def sum (a,b):
    return str(a+b)
@app.route('/min/<float:a>/<float:b>')
def min (a,b):
    return str(a-b)
@app.route('/mul/<float:a>/<float:b>')
def mul (a,b):
    return str(a*b)
@app.route('/deli/<float:a>/<float:b>')
def deli (a,b):
    if (b != 0):
        return str(a/b)
    else:
        return (str('делитель ноль'))
@app.route('/sin/<float:a>')
def sin (a):
    mode = request.args.get('mode', default='radians')
    if mode == 'degrees':
        return str(math.sin(math.radians(a)))
    else:
        return str(math.sin(a))
@app.route('/cos/<float:a>')
def cos(a):
    mode = request.args.get('mode', default='radians')
    if mode == 'degrees':
        return str(math.cos(math.radians(a)))
    else:
        return str(math.cos(a))
@app.route('/tg/<float:a>')
def tg(a):
    mode = request.args.get('mode', default='radians')
    if mode == 'degrees':
        return str(math.tan(math.radians(a)))
    else:
        return str(math.tan(a))
@app.route('/sqrt/<float:a>')
def sqrt (a):
    if (a < 0):
        return str('меньше 0')
    else:
        return str(math.sqrt(a))
@app.route('/ste/<float:a>/<float:b>')
def ste (a,b):
    return str(a**b)
@app.route('/ost/<float:a>/<float:b>')
def ost (a,b):
    if (b != 0):
        return str(a % b)
    else:
        return (str('делитель ноль'))
@app.route('/cel/<float:a>/<float:b>')
def cel (a,b):
    if (b != 0):
        return str(a // b)
    else:
        return (str('делитель ноль'))
#app.run(host='0.0.0.0', debug = True)