export class Choice {
    question: string;
    answers: string;
    
    constructor(question: string, answers: string) {
        this.question = question;
        this.answers = answers;
    }
}

export class Question{
    question: string;
    choices: string[];
    answer: Choice | null;

    constructor(question: string, choices: string[]) { 
        this.question = question;
        this.choices = choices;
        this.answer = null;
    }

    public pickChoice(choiceIndex: number): Choice {
        if (choiceIndex < 0 || choiceIndex >= this.choices.length) {
            throw new Error("Invalid choice index");
        }
        console.log(`Picked choice: ${this.choices[choiceIndex]} for question: ${this.question}`);
        this.answer = new Choice(this.question, this.choices[choiceIndex]);
        return this.answer;
    }

    public getAnswer(): Choice | null {
        return this.answer;
    }
}