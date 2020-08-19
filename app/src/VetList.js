import React, { Component } from 'react';
import { Button, ButtonGroup, Container, Table } from 'reactstrap';
import AppNavbar from './AppNavbar';
import { Link } from 'react-router-dom';

class VetList extends Component {

  constructor(props) {
    super(props);
    this.state = {vets: [], isLoading: true};
    this.remove = this.remove.bind(this);
  }

  componentDidMount() {
    this.setState({isLoading: true});

    fetch('/vets')
      .then(response => response.json())
      .then(data => this.setState({vets: data, isLoading: false}));
  }

  async remove(id) {
    await fetch(`/vets/${id}`, {
      method: 'DELETE',
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      }
    }).then(() => {
      let updatedVets = [...this.state.vets].filter(i => i.id !== id);
      this.setState({vets: updatedVets});
    });
  }

  render() {
    const {vets, isLoading} = this.state;

    if (isLoading) {
      return <p>Loading...</p>;
    }

    const vetList = vets.map(vet => {
      return <tr key={vet.id}>
        <td style={{whiteSpace: 'nowrap'}}>{vet.id}</td>
        <td style={{whiteSpace: 'nowrap'}}>{vet.firstName}</td>
        <td style={{whiteSpace: 'nowrap'}}>{vet.lastName}</td>
        <td>
          <ButtonGroup>
            <Button size="sm" color="primary" tag={Link} to={`/vets/${vet.id}/appointments`}>Appointments</Button>
            <Button size="sm" color="success" tag={Link} to={`/vets/${vet.id}`}>Edit</Button>
            <Button size="sm" color="danger" onClick={() => this.remove(vet.id)}>Delete</Button>
          </ButtonGroup>
        </td>
      </tr>
    });

    return (
      <div>
        <AppNavbar/>
        <Container fluid>
          <div className="float-left">
            <Button color="link" tag={Link} to="/">&lt; Back</Button>
          </div>
          <div className="float-right">
            <Button color="success" tag={Link} to="/vets/new">Add Vet</Button>
          </div>
          <h3 className="text-center">Vets</h3>
          <Table hover className="mt-4">
            <thead>
            <tr>
              <th width="20%">Id</th>
              <th>First Name</th>
              <th>Last Name</th>
              <th width="10%">Actions</th>
            </tr>
            </thead>
            <tbody>
            {vetList}
            </tbody>
          </Table>
        </Container>
      </div>
    );
  }
}

export default VetList;