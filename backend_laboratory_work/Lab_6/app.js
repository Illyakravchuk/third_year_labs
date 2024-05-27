import express from 'express'
import mongoose from 'mongoose'
import { graphqlHTTP } from 'express-graphql'
import schema from './schema/schema.js'

const app = express()
const PORT = process.env.PORT || 4000
const url = `mongodb+srv://ikravchukim13:vru1d2HGSCbAldlT@cluster0.yazpbgq.mongodb.net/`

app.use('/graphql', graphqlHTTP({
  schema,
  graphiql: true
}))

const start = async () => {
  await mongoose.connect(url)
  app.listen(PORT, (err) => {
    err ? console.log(err) : console.log(`Server is running on port: ${PORT}`)
  })
}

try {
  start()
} catch (e) {
  console.log(e)
}
