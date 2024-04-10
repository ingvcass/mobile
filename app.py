from flask import Flask, request
#from flack import request
import math

app = Flask('myapp')
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
app.run(host='0.0.0.0', debug = True)