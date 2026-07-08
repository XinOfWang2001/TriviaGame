import { Button, Modal } from "react-bootstrap";
import type { ResultDTO } from "./dto/ResultDTO";

export function ShowModal(result: ResultDTO, showState: boolean, onClose: () => void) {
    return (
        <Modal show={showState} onHide={onClose}>
        <Modal.Header closeButton>
          <Modal.Title>Modal heading</Modal.Title>
        </Modal.Header>
        <Modal.Body>
          <p>Invalid Answers: {result.invalidAnswers}</p>
          <p>Incorrectly Answered: {result.incorrectlyAnswered}</p>
          <p>Correctly Answered: {result.correctlyAnswered}</p>
        </Modal.Body>
        <Modal.Footer>
          <Button variant="secondary" onClick={onClose}>
            Close
          </Button>
          <Button variant="primary" onClick={onClose}>
            Save Changes
          </Button>
        </Modal.Footer>
      </Modal>
    )
}