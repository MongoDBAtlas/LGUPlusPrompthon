
const express = require('express');

const path = require('path');
const morgan = require('morgan');
const nunjuncks = require('nunjucks');
const cors = require('cors');


const indexRouter = require('./routes');
const handsonRouter = require('./routes/handson');

const app = express();
app.use(cors());
app.use(express.urlencoded({extended: false}));
app.use(express.json());
app.use("/",indexRouter);
app.use('/handson',handsonRouter);

app.set ('port', process.env.PORT || 3000);
nunjuncks.configure('views', {
    express: app,
    watch: true,
});

app.use(morgan('dev'));
app.use(express.static(path.join(__dirname,'public')));
app.use(express.json());
app.use(express.urlencoded({extended: false}));

app.use((req, res, next) => {
    const error = new Error(`${req.method} ${req.url} There is no router`);
    error.status = 404;
    next(error);
});

app.use((err, req, res, next) => {
    res.locals.message = err.message;
    res.locals.error = process.env.NODE_ENV !== 'production' ? err : {};
    res.status(err.status || 500);
    res.render('error');
});

app.listen(app.get('port'), () => {
    console.log(app.get('port'), ' is waiting to connect');
});