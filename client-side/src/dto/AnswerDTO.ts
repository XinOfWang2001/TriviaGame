export class AnswerDTO {
    question: string;
    answer: string;

    constructor(question: string, answer: string) {
        this.question = question;
        this.answer = answer;
    }
}

// eslint-disable-next-line @typescript-eslint/no-unused-vars
export class PostAnswerDTO{
    gameId: string;
    answers: AnswerDTO[];

    constructor(gameId: string, answers: AnswerDTO[]) {
        this.gameId = gameId;
        this.answers = answers;
    }
}