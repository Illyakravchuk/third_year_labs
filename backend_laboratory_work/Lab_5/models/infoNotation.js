import { model, Schema } from 'mongoose'

const infoСompaniesSchema = new Schema({ 
  companyName: {
    type: String,
    required: true
  },
  numberWorkers: {
    type: Number,
    required: true
  },
  contractName: {
    type: String,
    required: true
  },
  startDate: {
    type: Date,
    default: Date.now
  },
  endDate: {
    type: Date,
    default: Date.now
  }
})

export default model('infoСompanies', infoСompaniesSchema)