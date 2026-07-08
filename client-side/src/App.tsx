import { useState, useEffect, useRef } from 'react';
import { Badge, Button, ButtonGroup, Container, Form, ListGroup, ListGroupItem, Row, Col, Spinner } from 'react-bootstrap';
import './App.css'
import { Game } from './entities/Game'
import { Question } from './entities/Question'
import { ShowModal } from './ResultModal'
import type { ResultDTO } from './dto/ResultDTO'
import { GameService } from './services/GameService'

function App() {
  const [game, setGame] = useState<Game | null>(null);
  const [submitting, setSubmitting] = useState(false);
  const [resetting, setResetting] = useState(false);
  const [message, setMessage] = useState<string | null>(null);
  const [showModal, setShowModal] = useState(false);
  const [result, setResult] = useState<ResultDTO | null>(null);

  const gameService = useRef(new GameService());
  const hasFetched = useRef(false);

  useEffect(() => {
    if (hasFetched.current) return;
    hasFetched.current = true;

    const load = async () => {
      try {
        const g = await gameService.current.getGame();
        setGame(g);
      } catch (err) {
        setMessage(err instanceof Error ? err.message : 'Failed to load game');
      }
    };

    load();
  }, [gameService]);

  async function pushAnswers(currGame: Game) {
    setSubmitting(true);
    setMessage(null);

    try {
      // For now only show positive case using returned/calc result
      const positiveResult: ResultDTO = await gameService.current.submitAnswer(currGame);

      setResult(positiveResult);
      setShowModal(true);
      setMessage('Answers submitted successfully!');
    } catch (err) {
      setMessage(err instanceof Error ? err.message : 'Failed to submit answers');
    } finally {
      setSubmitting(false);
    }

    console.log(currGame.getAllChoices());
  }

  async function resetGame() {
    if (!game) return;
    setResetting(true);
    setMessage(null);

    try {
      // Re-fetch a fresh game from API to reset state
      const fresh = await gameService.current.getGame();
      setGame(fresh);
      setMessage('Game reset successfully!');
    } catch (err) {
      setMessage(err instanceof Error ? err.message : 'Failed to reset game');
    } finally {
      setResetting(false);
    }
  }

  function questionComponent(question: Question, questionIndex: number) {
    questionIndex++; // Increment questionIndex to start from 1 instead of 0
    return (
      <ListGroupItem className="m-2">
        <Col className="col-md-1">
          <Badge>{questionIndex}</Badge>
        </Col>
        <Col key={questionIndex + 1} className="col-md-11">
          <h2>{question.question}</h2>
          {question.choices.map((choice, choiceIndex) => (
            <Form.Check className="form-check-inline " key={choiceIndex}>
              <Form.Check.Input type="radio" name={`question-${questionIndex}`} value={choiceIndex} onChange={() => question.pickChoice(choiceIndex)} disabled={submitting} />
              <Form.Check.Label>{choice}</Form.Check.Label>
            </Form.Check>
          ))}
        </Col>
      </ListGroupItem>
    );
  }

  if (!game) {
    return (
      <Container className="mt-4">
        <p>Loading game...</p>
      </Container>
    );
  }

  return (
    <Container className="mt-4">
      <ul>
        <Row>
          <ListGroup>
            {
              game.questions.map((question, questionIndex) => (
                questionComponent(question, questionIndex)
              ))
            }
          </ListGroup>

        </Row>
      </ul>
      <Row>
        <ButtonGroup className="mt-3 align-items-end" >
          <Button className="btn-danger" onClick={() => resetGame()} disabled={(resetting || submitting)}>
            {resetting ? 'Resetting...' : 'Reset Game'}
          </Button>
          <Button className="btn-success" onClick={() => pushAnswers(game)} disabled={(resetting || submitting)}>
            {submitting && <Spinner animation="border" size="sm" className="me-2" />}
            {submitting ? 'Submitting...' : 'Post answers'}
          </Button>
        </ButtonGroup>
      </Row>

      {message && (
        <Row className="mt-3">
          <Col>
            <div className="alert alert-info" role="alert">{message}</div>
          </Col>
        </Row>
      )}

      {result && ShowModal(result, showModal, () => setShowModal(false))}
    </Container>
  )
}
export default App
