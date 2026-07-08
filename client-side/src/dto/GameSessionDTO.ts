/* eslint-disable @typescript-eslint/no-unused-vars */
export class QuestionDTO {
    question: string;
    choices: string[];

    constructor(question: string, choices: string[]) {
        this.question = question;
        this.choices = choices;
    }
}

export class GameSessionDTO {
    gameId: string;
    answers: QuestionDTO[];

    constructor(gameId: string, answers: QuestionDTO[]) {
        this.gameId = gameId;
        this.answers = answers;
    }
}