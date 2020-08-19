import React, { Component } from 'react';
import { Link, withRouter } from 'react-router-dom';
import { Button, Container, Form, FormGroup, Input, Label } from 'reactstrap';
import AppNavbar from './AppNavbar';
import DayPickerInput from 'react-day-picker/DayPickerInput';
import 'react-day-picker/lib/style.css';
import moment from 'moment';
import {formatDate, parseDate} from 'react-day-picker/moment';


class PetEdit extends Component {

  emptyItem = {
    name: '',
    birthDate: '',
    type: {
        id: '',
        name: ''
    }
  };

  constructor(props) {
    super(props);
    this.state = {
      item: this.emptyItem
    };
    this.handleChange = this.handleChange.bind(this);
    this.handleDayChange = this.handleDayChange.bind(this);
    this.handleSubmit = this.handleSubmit.bind(this);
  }

  async componentDidMount() {
    if (this.props.match.params.id !== 'new') {
      const pet = await (await fetch(`/pets/${this.props.match.params.id}`)).json();
      this.setState({item: pet});
    }
  }

  handleChange(event) {
    const target = event.target;
    const value = target.value;
    const name = target.name;
    let item = {...this.state.item};
    if(name === 'type') {
        item[name]['id'] = value;
        item[name]['name'] = target.options[target.selectedIndex].text;
    } else {
        item[name] = value;
    }
    this.setState({item});
  }

  handleDayChange(selectedDay, modifiers, dayPickerInput) {
    const input = dayPickerInput.getInput();
    let item = {...this.state.item};
    item.birthDate = input.value;
    this.setState({item});
  }

  async handleSubmit(event) {
    event.preventDefault();
    const {item} = this.state;

    await fetch('/pets', {
      method: (item.id) ? 'PUT' : 'POST',
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(item),
    });
    this.props.history.push('/pets');
  }

  render() {
    const dateChanged = this.handleChange;
    const {item} = this.state;
    const title = <h2>{item.id ? 'Edit Pet' : 'Add Pet'}</h2>;

    return <div>
      <AppNavbar/>
      <Container>
        {title}
        <Form onSubmit={this.handleSubmit}>
          <FormGroup>
            <Label for="name">Name</Label>
            <Input type="text" name="name" id="name" value={item.name || ''}
                   onChange={this.handleChange} autoComplete="name"/>
          </FormGroup>
          <FormGroup>
            <Label for="birthDate">Birth Date</Label>
            <DayPickerInput
                value={item.birthDate}
                onDayChange={this.handleDayChange}
                dayPickerProps={{
                  selectedDays: moment(item.birthDate).toDate(),
                  disabledDays: {after: new Date()}
                }}
                format="YYYY-MM-DD"
                formatDate={formatDate}
                parseDate={parseDate}
                inputProps={{
                  className: "form-control",
                  onChange: event => dateChanged(event)
                }}
                placeholder="yyyy-MM-dd"
                style={{width: "100%"}}
              />
          </FormGroup>
          <FormGroup>
            <Label for="type">Type</Label>
            <Input type="select" name="type" id="type" value={item.type.id || ''} onChange={this.handleChange}>
                <option value=''>Choose an option</option>
                <option value='1'>Cat</option>
                <option value='2'>Dog</option>
                <option value='3'>Lizard</option>
                <option value='4'>Snake</option>
                <option value='5'>Bird</option>
                <option value='6'>Hamster</option>
            </Input>
          </FormGroup>
          <FormGroup>
            <Button color="primary" type="submit">Save</Button>{' '}
            <Button color="secondary" tag={Link} to="/pets">Cancel</Button>
          </FormGroup>
        </Form>
      </Container>
    </div>
  }
}

export default withRouter(PetEdit);