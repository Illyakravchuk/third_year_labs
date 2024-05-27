import infoNotation from "../../models/infoNotation.js";

export default class Controller {
  static async getMainPage(req, res) {
    res.render('index', {
      title: 'Головна сторінка',
      isMain: true
    });
  }

  static async getAddNotationPage(req, res) {
    res.render('addNotation', {
      title: 'Додати нову картку',
      notationAddedMessage: req.query.notationAdded,
      isAdd: true
    });
  }

  static async getNotesPage(req, res) {
    const notation = await infoNotation.find();
    res.render('notes', {
      title: 'Записи',
      notation,
      isNotation: true
    });
  }

  static async createNewNotation(req, res) {
    try {
      const { companyName, numberWorkers, contractName, startDate, endDate } = req.body;
      const newNotation = new infoNotation({ companyName, numberWorkers, contractName, startDate, endDate });
      await newNotation.save();
      res.redirect('/add?notationAdded=true');
    } catch (e) {
      console.error(e);
      res.status(500).send('Помилка сервера');
    }
  }

  static async getNotationById(req, res) {
    const selectedNotation = await infoNotation.findById(req.params.id);
    res.render('viewNotation', {
      title: 'Технологічна картка',
      selectedNotation,
      isViewNotation: true
    });
  }

  static async getEditNotationPage(req, res) {
    const selectedNotation = await infoNotation.findById(req.params.id);
    let dateToStr = formatDate(selectedNotation.startDate);
    let dateToStr2 = formatDate(selectedNotation.endDate);
    res.render('editNotation', {
      title: 'Редагувати картку',
      selectedNotation,
      dateToStr,
      dateToStr2,
      isEdit: true
    });

    function formatDate(date) {
      const d = new Date(date);
      const year = d.getFullYear();
      let month = '' + (d.getMonth() + 1);
      let day = '' + d.getDate();
      if (month.length < 2) month = '0' + month;
      if (day.length < 2) day = '0' + day;
      return [year, month, day].join('-');
    }
  }

  static async editNotation(req, res) {
    try {
      const notationId = req.params.id;
      delete req.body.notationdId;
      await infoNotation.findByIdAndUpdate(notationId, req.body);
      res.json({ status: 0, notationId });
    } catch (e) {
      console.error(e);
      res.status(500).json({ status: 1, error: e });
    }
  }

  static async getDeleteNotationPage(req, res) {
    res.render('deleteNotation', {
      title: 'Видалити картку',
      layout: 'main',
      notationId: req.params.id,
      isDelete: true
    });
  }

  static async deletNotation(req, res) {
    try {
      const notationId = req.params.id;
      await infoNotation.findByIdAndDelete(notationId);
      res.json({ status: 0 });
    } catch (e) {
      console.error(e);
      res.status(500).json({ status: 1, error: e });
    }
  }

  static async getNotationInfoJSON(req, res) {
    const notationId = req.params.id;
    const selectedNotation = await infoNotation.findById(notationId);
    res.json(selectedNotation);
  }

  static async getAllInfoJSON(req, res) {
    const notation = await infoNotation.find();
    res.json(notation);
  }
}
