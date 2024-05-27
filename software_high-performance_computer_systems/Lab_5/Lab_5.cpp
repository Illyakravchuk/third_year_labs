// ПЗВПКС
// Лабораторна робота №5
// Варіант 14
// Z = (B*X)*(d*Е + R*(MZ*MR))*p 
// Кравчук Ілля Володимирович ІМ-13
// Дата 26.05.2024

#include <iostream>
#include <chrono>
#include <omp.h>

int generateScalar();
int* generateVector(int size);
int** generateMatrix(int size);
int scalarProduct(int* vec1, int* vec2, int startIdx, int endIdx);
int** extractSubMatrix(int** matrix, int section);
int* extractSubVector(int* vector, int section);
int** matrixMultiplication(int** mat1, int** mat2);
int* vectorMatrixMultiplication(int* vector, int** matrix);
int* scalarVectorMultiplication(int scalar, int* vector);
int* vectorAddition(int* vec1, int* vec2);
void computeTemp3(int* resultVec, int* temp3, int scalarD, int* vecE, int* vecR, int** matMZ, int** matMR, int threadId);
void printResultVector(int* vector, int size);
void freeMemory();

const int N = 1000;
const int P = 4;
const int H = N / P;

int a = 0;
int d;
int p;
int* vecZ = new int[N];
int* vecE;
int* vecR;
int* vecB;
int* vecX;
int** matMZ;
int** matMR;

int main() {
    auto startTime = std::chrono::high_resolution_clock::now();
    int a_i;
    int d_i;
    int p_i;
    int threadId;

    omp_set_num_threads(P);

#pragma omp parallel num_threads(P) private(threadId, a_i, d_i, p_i) shared(a, d, p, vecE, vecR, vecB, vecX, matMZ, matMR, vecZ)
    {
        threadId = omp_get_thread_num() + 1;

#pragma omp critical 
        {
            std::cout << "Thread_" << threadId << " is started" << std::endl;
        }

        switch (threadId) {
        case 1: // T1
            matMZ = generateMatrix(N); //Введення MZ
            vecE = generateVector(N);  //Введення E
            break;
        case 2: // T2
            vecR = generateVector(N);  //Введення R
            p = generateScalar();  //Введення p
            break;
        case 3: // T3
            vecB = generateVector(N);  //Введення B
            matMR = generateMatrix(N);  //Введення MR
            break;
        case 4: // T4
            vecX = generateVector(N);  //Введення X
            d = generateScalar();  //Введення d
            break;
        }

        // Бар'єр для синхронізації по введенню
#pragma omp barrier

        // Обчислення 1: ai = Bh * Xh
        int startIdx;
        int endIdx;
        switch (threadId) {
        case 1: // T1
            startIdx = 0;
            endIdx = H;
            a_i = scalarProduct(vecB, vecX, startIdx, endIdx); //Обчислення скалярного добутку в Т1
            break;
        case 2: // T2
            startIdx = H;
            endIdx = H * 2;
            a_i = scalarProduct(vecB, vecX, startIdx, endIdx); //Обчислення скалярного добутку в Т2
            break;
        case 3: // T3
            startIdx = H * 2;
            endIdx = H * 3;
            a_i = scalarProduct(vecB, vecX, startIdx, endIdx); //Обчислення скалярного добутку в Т3
            break;
        case 4: // T4
            startIdx = H * 3;
            endIdx = N;
            a_i = scalarProduct(vecB, vecX, startIdx, endIdx); //Обчислення скалярного добутку в Т4
            break;
        }

        //Обчислення 2  a = a + ai   -- КД1
#pragma omp critical(CS)
        {
            a = a + a_i;
        }

        // Бар'єр для синхронізаціЇ обчислення 2
#pragma omp barrier

        // копіювання a_i = a    --КД2
#pragma omp critical(CS)
        {
            a_i = a;
        }

        // копіювання d_i = d    --КД3
#pragma omp critical(CS)
        {
            d_i = d;
        }

        // копіювання p_i = p    --КД4
#pragma omp critical(CS)
        {
            p_i = p;
        }

        int* temp3 = new int[H];

        computeTemp3(vecZ, temp3, d_i, vecE, vecR, matMZ, matMR, threadId);
        // Обчислення 3  vecZ = a_i * temp3 * p_i;
#pragma omp parallel for
        for (int i = 0; i < N; ++i) {
            vecZ[i] = a_i * temp3[i % H] * p_i;
        }

        delete[] temp3;

        // Бар'єр для синхронізаціЇ виведення Z
#pragma omp barrier

        if (threadId == 2) {
#pragma omp critical
            {
                //Вивід Z
                std::cout << "Z vector" << std::endl;
                printResultVector(vecZ, N);
                std::cout << std::endl;
            }
        }

#pragma omp critical
        {
            std::cout << "Thread_" << threadId << " is finished" << std::endl;
        }
    }

    auto endTime = std::chrono::high_resolution_clock::now();
    auto duration = std::chrono::duration_cast<std::chrono::milliseconds>(endTime - startTime);
    std::cout << "Program execution time: " << duration.count() << "ms" << std::endl;

    freeMemory();

    return 0;
}


int generateScalar() {
    return 1;
}

int* generateVector(int size) {
    int* res = new int[size];
    for (int i = 0; i < size; i++) {
        res[i] = 1;
    }
    return res;
}

int** generateMatrix(int size) {
    int** res = new int* [size];
    for (int i = 0; i < size; i++) {
        res[i] = new int[size];
        for (int j = 0; j < size; j++) {
            res[i][j] = 1;
        }
    }
    return res;
}

void printResultVector(int* vector, int size) {
    for (int i = 0; i < size; ++i) {
        std::cout << vector[i] << " ";
    }
    std::cout << std::endl;
}

int scalarProduct(int* vec1, int* vec2, int startIdx, int endIdx) {
    int result = 0;
    for (int i = startIdx; i < endIdx; ++i) {
        result += vec1[i] * vec2[i];
    }
    return result;
}

int** extractSubMatrix(int** matrix, int section) {
    int** res = new int* [N];
    for (int i = 0; i < N; i++) {
        res[i] = new int[H];
    }
    int startIdx = (section - 1) * H;
    int endIdx = startIdx + H;
    for (int j = startIdx; j < endIdx; ++j) {
        for (int i = 0; i < N; ++i) {
            res[i][j - startIdx] = matrix[i][j];
        }
    }
    return res;
}

int* extractSubVector(int* vector, int section) {
    int* res = new int[H];
    int startIdx = (section - 1) * H;
    int endIdx = startIdx + H;
    for (int i = startIdx; i < endIdx; ++i) {
        res[i - startIdx] = vector[i];
    }
    return res;
}

int** matrixMultiplication(int** mat1, int** mat2) {
    int** res = new int* [N];
    for (int i = 0; i < N; i++) {
        res[i] = new int[N];
    }
    for (int i = 0; i < N; ++i) {
        for (int j = 0; j < H; ++j) {
            res[i][j] = 0;
            for (int k = 0; k < N; ++k) {
                res[i][j] += mat1[i][k] * mat2[k][j];
            }
        }
    }
    return res;
}

int* vectorMatrixMultiplication(int* vector, int** matrix) {
    int* res = new int[H];
    for (int i = 0; i < H; i++) {
        int sum = 0;
        for (int j = 0; j < N; j++) {
            sum += matrix[j][i] * vector[j];
        }
        res[i] = sum;
    }
    return res;
}

int* scalarVectorMultiplication(int scalar, int* vector) {
    int* res = new int[N];
    for (int i = 0; i < N; i++) {
        res[i] = scalar * vector[i];
    }
    return res;
}

int* vectorAddition(int* vec1, int* vec2) {
    int* res = new int[H];
    for (int i = 0; i < H; i++) {
        res[i] = vec1[i] + vec2[i];
    }
    return res;
}

// Обчислення  temp3 = (d * E + R * (MZ * MR));
void computeTemp3(int* resultVec,  int* temp3, int scalarD, int* vecE, int* vecR, int** matMZ, int** matMR, int threadId) {
    int** subMatMR = extractSubMatrix(matMR, threadId);
    int* subVecE = extractSubVector(vecE, threadId);

    int* temp2 = vectorMatrixMultiplication(vecR, matrixMultiplication(matMZ, subMatMR));

    int* temp1 = scalarVectorMultiplication(scalarD, subVecE);
    int* temp3Part = vectorAddition(temp1, temp2);

    for (int i = 0; i < H; i++) {;
        temp3[i] = temp3Part[i];
    }

    for (int i = 0; i < N; i++) {
        delete[] subMatMR[i];
    }
    delete[] subMatMR;
    delete[] subVecE;
    delete[] temp1;
    delete[] temp2;
    delete[] temp3Part;
}

void freeMemory() {
    for (int i = 0; i < N; i++) {
        delete[] matMZ[i];
        delete[] matMR[i];
    }
    delete[] matMZ;
    delete[] matMR;
    delete[] vecB;
    delete[] vecE;
    delete[] vecR;
    delete[] vecX;
}
