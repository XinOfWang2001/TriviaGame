/* eslint-disable @typescript-eslint/no-unused-vars */
import type { Question, Choice } from "./Question";

export class Game {
    gameId: string;
    questions: Question[];

    constructor(gameId: string, questions: Question[]) {
        this.gameId = gameId;
        this.questions = questions;
    }

    public getAllChoices(): (Choice | null) [] {
        return this.questions.map((question) => question.getAnswer());
    }
}