import { makeExecutableSchema } from '@graphql-tools/schema'
import resolvers from './resolver.js'
import { readFileSync } from 'fs'
import { dirname, resolve } from 'path'
import { fileURLToPath } from 'url'

// Отримуємо поточне ім'я файлу та каталог
const __filename = fileURLToPath(import.meta.url)
const __dirname = dirname(__filename)

// Визначаємо шлях до файлу схеми GraphQL
const schemaFilePath = resolve(__dirname, 'schema.graphql')

// Читаємо вміст файлу схеми
const typeDefs = readFileSync(schemaFilePath, 'utf-8')

// Створюємо схему GraphQL з використанням визначень типів та резольверів
const schema = makeExecutableSchema({ typeDefs, resolvers })

export default schema
