import { Schema, model } from 'mongoose'

const FirmInfo = new Schema({
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

export default model('infoFirm', FirmInfo)
