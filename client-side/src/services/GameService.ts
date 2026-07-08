import { Game } from "../entities/Game";
import { PostAnswerDTO, AnswerDTO } from "../dto/AnswerDTO";
import { Question } from "../entities/Question";
import type { ResultDTO } from "../dto/ResultDTO";

export class GameService {
    private readonly apiUrl: string;

    constructor(apiUrl: string = "http://localhost:8080") {
        this.apiUrl = apiUrl;
    }

    public async getGame(): Promise<Game> {
        const response = await fetch(this.apiUrl + "/game");
        if (!response.ok) {
            throw new Error(`Failed to fetch game: ${response.statusText}`);
        }

        let data: any;
        try {
            data = await response.json();
        } catch (err) {
            console.error('GameService.getGame: invalid JSON', err);
            throw new Error('Invalid JSON in game response', { cause: err });
        }

        // Debug log to help diagnose shape issues
        // eslint-disable-next-line no-console
        console.log('GameService.getGame response:', data);

        // Support multiple possible shapes (answers, questions, options)
        const answersArray: any[] = Array.isArray(data.answers)
            ? data.answers
            : Array.isArray(data.questions)
            ? data.questions
            : [];

        const questions = answersArray.map((item) => {
            const qText = typeof item.question === 'string' ? item.question : item.text || item.title || '';
            const choices = Array.isArray(item.choices)
                ? item.choices
                : Array.isArray(item.options)
                ? item.options
                : [];
            return new Question(qText, choices);
        });

        const gameId = data.gameId || data.id || 'game-1';
        const game = new Game(gameId, questions);
        return game;
    }

    public async submitAnswer(game: Game): Promise<ResultDTO> {
        const answers = game.getAllChoices().map((choice) => new AnswerDTO(choice?.question || "", choice?.answers || ""));
        
        const postAnswerDTO = new PostAnswerDTO(game.gameId, answers);

        const response = await fetch(`${this.apiUrl}/game/solve`, {
            method: "PUT",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(postAnswerDTO),
        });

        if (!response.ok) {
            throw new Error(`Failed to submit answer: ${response.statusText}`);
        }
        const result: ResultDTO = await response.json();
        // Optionally consume response body if backend returns useful data
        return result;
    }
}