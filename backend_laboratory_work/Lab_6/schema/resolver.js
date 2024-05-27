import FirmInfo from '../models/firmInfo.js'

const resolvers = {
  Query: {
    getFirmById: async (_, { id }) => {
      return await FirmInfo.findById(id)
    },
    getAllFirms: async () => {
      return await FirmInfo.find()
    }
  },
  Mutation: {
    addFirm: async (_, { firm: { companyName, numberWorkers, contractName, startDate, endDate } }) => {
      const firm = new FirmInfo({
        companyName,
        numberWorkers,
        contractName,
        startDate: startDate ? startDate : new Date(Date.now()),
        endDate: endDate ? endDate : new Date(Date.now())
      })
      try {
        await firm.save()
        return await FirmInfo.find()
      } catch (err) {
        throw new Error(err)
      }
    },
    updateFirm: async (_, { id, body }) => {
      try {
        await FirmInfo.findByIdAndUpdate(id, body, { new: true }); // Оновлення та отримання оновленого документу
        return FirmInfo.findById(id); // Повернення оновленого документу
      } catch (err) {
        throw new Error(err);
      }
    },
    deleteFirm: async (_, { id }) => {
      await FirmInfo.findByIdAndDelete(id)
      return await FirmInfo.find()
    },
  },
  Firm: {
  }
}

export default resolvers
