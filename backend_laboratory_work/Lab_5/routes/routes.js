import { Router } from 'express';
import Controller from './controller/Controller.js';

const router = Router();

router.get('/', Controller.getMainPage);

router.get('/add', Controller.getAddNotationPage);
router.post('/add', Controller.createNewNotation);

router.get('/notes', Controller.getNotesPage);
router.get('/notes/:id', Controller.getNotationById);

router.get('/edit/:id', Controller.getEditNotationPage);
router.put('/edit/:id', Controller.editNotation);

router.get('/delete/:id', Controller.getDeleteNotationPage);
router.delete('/delete/:id', Controller.deletNotation);

router.get('/api/notes', Controller.getAllInfoJSON);
router.get('/api/notes/:id', Controller.getNotationInfoJSON);

export default router;
